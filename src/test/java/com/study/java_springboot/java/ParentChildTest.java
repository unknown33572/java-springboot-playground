package com.study.java_springboot.java;

import com.study.java_springboot.java.abstract_test.Child;
import com.study.java_springboot.java.abstract_test.Parent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Parent/Child 클래스의 상속과 다형성을 검증하는 테스트.
 *
 * 학습 포인트:
 * - @BeforeEach / @AfterEach : 각 테스트 전후에 실행되는 셋업/정리 코드
 * - @Test                    : 테스트 메서드 표시
 * - @DisplayName             : 테스트 이름을 한글로 보기 좋게 표시
 * - assertEquals()           : 기대값과 실제값 비교
 * - assertInstanceOf()       : 타입 검증
 */
class ParentChildTest {

    // System.out 출력을 캡처하기 위한 도구
    private final ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        // 각 테스트 전에 System.out을 가로채서 outputCapture에 저장
        originalOut = System.out;
        System.setOut(new PrintStream(outputCapture));
    }

    @AfterEach
    void tearDown() {
        // 각 테스트 후에 System.out을 원래대로 복원
        System.setOut(originalOut);
    }

    // ─── 1. 기본 동작 테스트 ───

    @Test
    @DisplayName("Parent.method1()은 'Parent method1'을 출력한다")
    void parent_method1_prints_parent_message() {
        Parent parent = new Parent();
        parent.method1();
        assertEquals("Parent method1", outputCapture.toString().trim());
    }

    @Test
    @DisplayName("Parent.method2()는 'Parent method2'를 출력한다")
    void parent_method2_prints_parent_message() {
        Parent parent = new Parent();
        parent.method2();
        assertEquals("Parent method2", outputCapture.toString().trim());
    }

    @Test
    @DisplayName("Child.method3()은 'Child method2'를 출력한다")
    void child_method3_prints_child_message() {
        Child child = new Child();
        child.method3();
        assertEquals("Child method2", outputCapture.toString().trim());
    }

    // ─── 2. 오버라이딩(Override) 테스트 ───

    @Test
    @DisplayName("Child는 method1()을 오버라이드하여 'Child method1'을 출력한다")
    void child_overrides_method1() {
        Child child = new Child();
        child.method1();
        // Parent의 "Parent method1"이 아니라 Child의 "Child method1"이 나와야 한다
        assertEquals("Child method1", outputCapture.toString().trim());
    }

    @Test
    @DisplayName("Child는 method2()를 오버라이드하지 않으므로 Parent의 method2()가 실행된다")
    void child_inherits_method2_from_parent() {
        Child child = new Child();
        child.method2();
        // Child에 method2()가 없으므로 Parent의 것을 그대로 상속받는다
        assertEquals("Parent method2", outputCapture.toString().trim());
    }

    // ─── 3. 다형성(Polymorphism) 테스트 ───

    @Test
    @DisplayName("다형성: Parent 타입 변수에 Child 객체를 담아도 Child의 method1()이 실행된다")
    void polymorphism_parent_reference_calls_child_method() {
        Parent poly = new Child(); // 부모 타입으로 자식 객체를 참조
        poly.method1();
        // 런타임에 실제 객체 타입(Child)의 메서드가 호출된다 = 동적 바인딩
        assertEquals("Child method1", outputCapture.toString().trim());
    }

    @Test
    @DisplayName("다형성: Parent 타입으로 참조해도 method2()는 Parent 것이 실행된다 (Child가 오버라이드 안 했으므로)")
    void polymorphism_parent_reference_calls_inherited_method2() {
        Parent poly = new Child();
        poly.method2();
        assertEquals("Parent method2", outputCapture.toString().trim());
    }

    // ─── 4. 타입 검증 테스트 ───

    @Test
    @DisplayName("Child는 Parent의 인스턴스이다 (instanceof)")
    void child_is_instance_of_parent() {
        Child child = new Child();
        assertInstanceOf(Parent.class, child);
    }

    @Test
    @DisplayName("Parent는 Child의 인스턴스가 아니다")
    void parent_is_not_instance_of_child() {
        Parent parent = new Parent();
        assertFalse(parent instanceof Child);
    }

    @Test
    @DisplayName("Parent 타입 변수에 담긴 Child 객체는 Child로 캐스팅할 수 있다")
    void parent_reference_to_child_can_be_cast() {
        Parent poly = new Child();
        assertInstanceOf(Child.class, poly);

        // 다운캐스팅 후 Child 고유 메서드 호출 가능
        Child casted = (Child) poly;
        casted.method3();
        assertEquals("Child method2", outputCapture.toString().trim());
    }
}
