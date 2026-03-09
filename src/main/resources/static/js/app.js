// SPA Router
function navigate() {
    const hash = window.location.hash.replace('#', '') || 'dashboard';

    // Update nav active state
    document.querySelectorAll('.nav-link').forEach(link => {
        link.classList.toggle('active', link.dataset.page === hash);
    });

    destroyCharts();

    switch (hash) {
        case 'sites':
            renderSites();
            break;
        case 'dashboard':
        default:
            renderDashboard();
            break;
    }
}

window.addEventListener('hashchange', navigate);
window.addEventListener('DOMContentLoaded', navigate);
