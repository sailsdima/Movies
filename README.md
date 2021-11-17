# Shows

The app is separated into 2 modules and uses **MVI** architecture.

Under the hood the app uses **Hilt** for dependency injection, **Kotlin flow and coroutines** for asynchronously support, **DataStore** for saving data, **Navigation Component** for natigating between fragments


The app contains 2 fragments:

_MainFragment_ shows the list of shows loaded from remote api (https://api.tvmaze.com), using **Paging library 3** (for loading by pages), **Concat adapter** (for making 'Genre' label to be scrollable inside show item), **DataStore** (for adding show to favorites list). 

_ShowFragment_ shows detailed info about show. It uploads full model of show and it`s episodes from remote api and displays it. Also this screen contains a button to add show to favorites.

