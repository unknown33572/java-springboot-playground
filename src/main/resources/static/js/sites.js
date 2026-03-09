async function renderSites() {
    const app = document.getElementById('app');
    app.innerHTML = '<div class="loading">Loading...</div>';

    try {
        const sites = await API.getSites();

        app.innerHTML = `
            <div class="page-header">
                <h2>Monitored Sites</h2>
                <button class="btn btn-primary" onclick="openAddSiteModal()">+ Add Site</button>
            </div>

            <div class="sites-grid">
                ${sites.length === 0 ? '<p class="empty-state">No sites added yet. Click "Add Site" to get started.</p>' : ''}
                ${sites.map(site => `
                    <div class="card site-card">
                        <div class="site-header">
                            <div>
                                <h3 class="site-name">${site.name}</h3>
                                <a class="site-url" href="${site.url}" target="_blank">${site.url}</a>
                            </div>
                            <span class="badge ${site.active ? 'badge-active' : 'badge-inactive'}">
                                ${site.active ? 'Active' : 'Inactive'}
                            </span>
                        </div>
                        <p class="site-desc">${site.description || 'No description'}</p>
                        <div class="site-stats">
                            <span class="stat-chip">${site.keywordCount} keywords</span>
                        </div>
                        <div class="site-actions">
                            <button class="btn btn-sm btn-outline" onclick="viewSiteKeywords(${site.id})">View Keywords</button>
                            <button class="btn btn-sm btn-outline" onclick="openEditSiteModal(${site.id})">Edit</button>
                            <button class="btn btn-sm btn-danger" onclick="confirmDeleteSite(${site.id}, '${site.name}')">Delete</button>
                        </div>
                    </div>
                `).join('')}
            </div>

            <div id="siteKeywords"></div>
        `;

    } catch (err) {
        app.innerHTML = `<div class="error">Failed to load sites: ${err.message}</div>`;
    }
}

function openAddSiteModal() {
    document.getElementById('modalTitle').textContent = 'Add Site';
    document.getElementById('siteId').value = '';
    document.getElementById('siteForm').reset();
    document.getElementById('siteModal').classList.remove('hidden');
}

async function openEditSiteModal(id) {
    try {
        const site = await API.getSite(id);
        document.getElementById('modalTitle').textContent = 'Edit Site';
        document.getElementById('siteId').value = id;
        document.getElementById('siteName').value = site.name;
        document.getElementById('siteUrl').value = site.url;
        document.getElementById('siteDesc').value = site.description || '';
        document.getElementById('siteModal').classList.remove('hidden');
    } catch (err) {
        alert('Failed to load site: ' + err.message);
    }
}

function closeSiteModal() {
    document.getElementById('siteModal').classList.add('hidden');
}

async function handleSiteSubmit(e) {
    e.preventDefault();
    const id = document.getElementById('siteId').value;
    const data = {
        name: document.getElementById('siteName').value,
        url: document.getElementById('siteUrl').value,
        description: document.getElementById('siteDesc').value
    };

    try {
        if (id) {
            await API.updateSite(id, data);
        } else {
            await API.createSite(data);
        }
        closeSiteModal();
        renderSites();
    } catch (err) {
        alert('Failed to save: ' + err.message);
    }
}

async function confirmDeleteSite(id, name) {
    if (!confirm(`Delete "${name}"? This cannot be undone.`)) return;
    try {
        await API.deleteSite(id);
        renderSites();
    } catch (err) {
        alert('Failed to delete: ' + err.message);
    }
}

async function viewSiteKeywords(siteId) {
    const container = document.getElementById('siteKeywords');
    container.innerHTML = '<div class="loading">Loading keywords...</div>';

    try {
        const site = await API.getSite(siteId);
        const keywords = site.keywords;

        container.innerHTML = `
            <div class="card keyword-detail-card">
                <div class="keyword-detail-header">
                    <h3>Keywords - ${site.name}</h3>
                    <button class="btn btn-sm btn-outline" onclick="document.getElementById('siteKeywords').innerHTML=''">Close</button>
                </div>
                ${keywords.length === 0 ? '<p class="empty-state">No keywords found.</p>' : `
                    <div class="table-wrapper">
                        <table class="data-table">
                            <thead>
                                <tr>
                                    <th>Keyword</th>
                                    <th>Total Mentions</th>
                                    <th>Relevance</th>
                                    <th>Trend</th>
                                </tr>
                            </thead>
                            <tbody>
                                ${keywords.map(k => `
                                    <tr>
                                        <td><strong>${k.word}</strong></td>
                                        <td>${k.totalMentions.toLocaleString()}</td>
                                        <td>${(k.relevanceScore * 100).toFixed(0)}%</td>
                                        <td>
                                            <button class="btn btn-sm btn-outline" onclick="showKeywordTrend(${k.id}, '${k.word}')">
                                                View Trend
                                            </button>
                                        </td>
                                    </tr>
                                `).join('')}
                            </tbody>
                        </table>
                    </div>
                `}
                <div id="keywordTrendContainer"></div>
            </div>
        `;
    } catch (err) {
        container.innerHTML = `<div class="error">Failed to load keywords: ${err.message}</div>`;
    }
}

async function showKeywordTrend(keywordId, word) {
    const container = document.getElementById('keywordTrendContainer');
    container.innerHTML = `
        <div class="keyword-trend-section">
            <h4>${word} - 30 Day Trend</h4>
            <div class="chart-container">
                <canvas id="keywordTrendChart"></canvas>
            </div>
        </div>
    `;

    try {
        const stats = await API.getKeywordStats(keywordId, 30);
        const trendData = {};
        trendData[word] = stats;
        if (trendChartInstance) { trendChartInstance.destroy(); trendChartInstance = null; }
        renderTrendChart('keywordTrendChart', trendData);
    } catch (err) {
        container.innerHTML = `<div class="error">Failed to load trend: ${err.message}</div>`;
    }
}
