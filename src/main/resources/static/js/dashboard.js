async function renderDashboard() {
    const app = document.getElementById('app');
    app.innerHTML = '<div class="loading">Loading...</div>';

    try {
        const [summary, topKeywords] = await Promise.all([
            API.getDashboardSummary(),
            API.getTopKeywords(10)
        ]);

        app.innerHTML = `
            <div class="page-header">
                <h2>Dashboard</h2>
                <p class="page-desc">Keyword monitoring overview across all sites</p>
            </div>

            <div class="summary-cards">
                <div class="card summary-card">
                    <div class="card-value">${summary.totalSites}</div>
                    <div class="card-label">Total Sites</div>
                </div>
                <div class="card summary-card">
                    <div class="card-value">${summary.activeSites}</div>
                    <div class="card-label">Active Sites</div>
                </div>
                <div class="card summary-card">
                    <div class="card-value">${summary.totalKeywords}</div>
                    <div class="card-label">Keywords</div>
                </div>
                <div class="card summary-card accent">
                    <div class="card-value">${summary.topKeyword}</div>
                    <div class="card-label">Top Keyword</div>
                </div>
            </div>

            <div class="charts-grid">
                <div class="card chart-card wide">
                    <h3>Keyword Trends (30 Days)</h3>
                    <div class="chart-container">
                        <canvas id="trendChart"></canvas>
                    </div>
                </div>
                <div class="card chart-card">
                    <h3>Top Keywords by Mentions</h3>
                    <div class="chart-container">
                        <canvas id="barChart"></canvas>
                    </div>
                </div>
                <div class="card keyword-table-card">
                    <h3>Keyword Rankings</h3>
                    <div class="table-wrapper">
                        <table class="data-table">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Keyword</th>
                                    <th>Mentions</th>
                                    <th>Relevance</th>
                                    <th>Site</th>
                                </tr>
                            </thead>
                            <tbody>
                                ${topKeywords.map((k, i) => `
                                    <tr>
                                        <td>${i + 1}</td>
                                        <td><strong>${k.word}</strong></td>
                                        <td>${k.totalMentions.toLocaleString()}</td>
                                        <td>
                                            <div class="relevance-bar">
                                                <div class="relevance-fill" style="width:${k.relevanceScore * 100}%"></div>
                                            </div>
                                        </td>
                                        <td class="text-muted">${k.siteName}</td>
                                    </tr>
                                `).join('')}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        `;

        // Load trend chart with top 5 keywords
        const top5Ids = topKeywords.slice(0, 5).map(k => k.id);
        if (top5Ids.length > 0) {
            const trendData = await API.getTrend(top5Ids, 30);
            destroyCharts();
            renderTrendChart('trendChart', trendData);
            renderBarChart('barChart', topKeywords);
        }

    } catch (err) {
        app.innerHTML = `<div class="error">Failed to load dashboard: ${err.message}</div>`;
    }
}
