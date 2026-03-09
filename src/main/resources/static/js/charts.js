const CHART_COLORS = [
    '#e94560', '#0f3460', '#53d8fb', '#f7b731',
    '#26de81', '#a55eea', '#fd9644', '#2bcbba',
    '#fc5c65', '#4b7bec'
];

let trendChartInstance = null;
let barChartInstance = null;

function destroyCharts() {
    if (trendChartInstance) { trendChartInstance.destroy(); trendChartInstance = null; }
    if (barChartInstance) { barChartInstance.destroy(); barChartInstance = null; }
}

function renderTrendChart(canvasId, trendData) {
    const ctx = document.getElementById(canvasId);
    if (!ctx) return;

    const keywords = Object.keys(trendData);
    if (keywords.length === 0) return;

    const labels = trendData[keywords[0]].map(s => s.recordDate);

    const datasets = keywords.map((keyword, i) => ({
        label: keyword,
        data: trendData[keyword].map(s => s.mentionCount),
        borderColor: CHART_COLORS[i % CHART_COLORS.length],
        backgroundColor: CHART_COLORS[i % CHART_COLORS.length] + '20',
        tension: 0.3,
        fill: false,
        pointRadius: 2,
        borderWidth: 2
    }));

    trendChartInstance = new Chart(ctx, {
        type: 'line',
        data: { labels, datasets },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            interaction: { mode: 'index', intersect: false },
            plugins: {
                legend: {
                    labels: { color: '#c8c8d0', font: { size: 12 } }
                },
                tooltip: {
                    backgroundColor: '#1e2a3a',
                    titleColor: '#fff',
                    bodyColor: '#c8c8d0',
                    borderColor: '#2a3a4e',
                    borderWidth: 1
                }
            },
            scales: {
                x: {
                    ticks: { color: '#888', maxTicksLimit: 10 },
                    grid: { color: '#2a2a3e' }
                },
                y: {
                    ticks: { color: '#888' },
                    grid: { color: '#2a2a3e' }
                }
            }
        }
    });
}

function renderBarChart(canvasId, keywords) {
    const ctx = document.getElementById(canvasId);
    if (!ctx) return;

    const sorted = [...keywords].sort((a, b) => b.totalMentions - a.totalMentions).slice(0, 10);

    barChartInstance = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: sorted.map(k => k.word),
            datasets: [{
                label: 'Total Mentions',
                data: sorted.map(k => k.totalMentions),
                backgroundColor: CHART_COLORS.map(c => c + 'CC'),
                borderColor: CHART_COLORS,
                borderWidth: 1,
                borderRadius: 4
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            indexAxis: 'y',
            plugins: {
                legend: { display: false },
                tooltip: {
                    backgroundColor: '#1e2a3a',
                    titleColor: '#fff',
                    bodyColor: '#c8c8d0'
                }
            },
            scales: {
                x: {
                    ticks: { color: '#888' },
                    grid: { color: '#2a2a3e' }
                },
                y: {
                    ticks: { color: '#c8c8d0', font: { size: 12 } },
                    grid: { display: false }
                }
            }
        }
    });
}
