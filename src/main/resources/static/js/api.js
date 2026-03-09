const API = {
    async get(path) {
        const res = await fetch(path);
        if (!res.ok) throw new Error(`GET ${path}: ${res.status}`);
        return res.json();
    },

    async post(path, body) {
        const res = await fetch(path, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body)
        });
        if (!res.ok) throw new Error(`POST ${path}: ${res.status}`);
        return res.json();
    },

    async put(path, body) {
        const res = await fetch(path, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body)
        });
        if (!res.ok) throw new Error(`PUT ${path}: ${res.status}`);
        return res.json();
    },

    async del(path) {
        const res = await fetch(path, { method: 'DELETE' });
        if (!res.ok) throw new Error(`DELETE ${path}: ${res.status}`);
    },

    // Convenience methods
    getSites: () => API.get('/api/sites'),
    getSite: (id) => API.get(`/api/sites/${id}`),
    createSite: (data) => API.post('/api/sites', data),
    updateSite: (id, data) => API.put(`/api/sites/${id}`, data),
    deleteSite: (id) => API.del(`/api/sites/${id}`),

    getTopKeywords: (limit = 10) => API.get(`/api/keywords/top?limit=${limit}`),
    getKeywordStats: (id, days = 30) => API.get(`/api/keywords/${id}/stats?days=${days}`),
    getKeywordsBySite: (siteId) => API.get(`/api/keywords/site/${siteId}`),

    getDashboardSummary: () => API.get('/api/dashboard/summary'),
    getTrend: (ids, days = 30) => API.get(`/api/dashboard/trend?keywordIds=${ids.join(',')}&days=${days}`)
};
