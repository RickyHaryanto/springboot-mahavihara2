let CACHE_NAME = 'my-cache';
let urlsToCache=[
    'home.html'
];



self.addEventListener('install',function(event){
    event.waitUntil(
        caches.open(CACHE_NAME)
        .then(function(cache){
            console.log('Opened Cache');
            return cache.addAll(urlsToCache);
        })
    );
});

self.addEventListener('activate', function(event){
    var cacheWhiteList = ['pigment'];
    event.waitUntil(
        caches.keys().then(function(cacheNames){
            return Promise.all(
                cacheNames.map(function(cacheName){
                    if(cacheWhiteList.indexOf(cacheName) === -1){
                        return caches.delete(cacheName)
                    }
                })
            );
        })
    );
});

self.addEventListener('fetch', function(event){
    event.respondWith(
        caches.match(event.request)
        .then(function(response){
            if(response){
                return response;
            }
            return fetch(event.request);
        })
    );
});

/*
self.addEventListener('install', () => {
    console.log(`installing service worker`);
})

self.addEventListener('activate', () => {
    console.log(`activating service worker`);
})

self.addEventListener('fetch', event => {
    console.log(`fetching...
    ${event.request.url}`);
})
*/