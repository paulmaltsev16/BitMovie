# BitMovie
<p align="center">
  <img src="https://github.com/paulmaltsev16/BitMovie/assets/38934377/10e3f85f-185d-4f1e-85cf-e806fe4700ee" alt="App logo">
</p>

## Stack:

Kotlin, Compose, Flow, Retrofit, Coroutines, Material3, JUnit, TMDB (The movie database).

## Summary

The project downloads a list of movies from The Movie Database API and displays it in three
categories. The user can check movie details by clicking on its poster. The user can also add a
movie to the list of favorites. The latest technologies were used, such as Coroutine, Compose, Flow
to speed up development and improve the code's readability.

### Implemented features

1. Latest movies as a list with a poster image with a filter [Upcoming, Top Rated, No Playing]
2. When an item on the list is clicked it should open a new screen that shows the movie image,
   description, year and rating.
3. This screen should be opened with an animation
4. There should be another tab in the app which shows the movies the user liked (saved movies)
5. Focus on architectures, efficiency, and latest approaches.
6. The list should allow for infinite scrolling (lazy loading), loading more items when the user
   reaches the end (or near it, for optimization)
7. Write Unit Tests
8. Provide a clear user experience when there is no internet connection.
9. Follow HIG for iOS and Matirial Design for Android

### Requires additional research

1. Cache images, set expiration of 1 day.- Coil, a recommended library for remote image management,
   has a caching mechanism but does not allow developers to change it. One possible solution is to
   implement another library or manually delete the directory from the app's internals.
2. Present a Play button to play the trailer in a controller. The Movie Detail API returns data
   without a video url, requires additional research to find out how to get the trailer.

### Additional features can be implemented

1. To receive genre data, we need to make an additional request.
2. Implement mechanism to make a request for data in the specified language.
3. Downloading movie details from the API is not considered best practice.
4. We can create an additional call to retrieve a list of movie posters and display them in the
   movie details.
5. On image click, open the full-size image with zoom options.
6. Favorite movies have to be stored on a remote source that requires user authentication.
7. Because there is shared preference to store favorites, there are performance issues.
8. Use Dagger Hilt for dependency injection.
9. After screen rotate scroll state should be store for better user experience.
10. Requires additional setup to support dark mode.

Screenshots:

<div align="center">
<img src="https://github.com/paulmaltsev16/BitMovie/assets/38934377/3dbc3145-ca35-4b13-9ea2-161e17e64e75" alt="Screenshor_1" width="150" height="300">
<img src="https://github.com/paulmaltsev16/BitMovie/assets/38934377/08938293-db49-4fd4-bca8-0d137e23c630" alt="Screenshor_1" width="150" height="300">
<img src="https://github.com/paulmaltsev16/BitMovie/assets/38934377/030cb9be-ba07-4f88-903b-1a78d84835c0" alt="Screenshor_1" width="150" height="300">
<img src="https://github.com/paulmaltsev16/BitMovie/assets/38934377/306dda44-fc38-4da9-9128-0a8167524084" alt="Screenshor_1" width="150" height="300">
<img src="https://github.com/paulmaltsev16/BitMovie/assets/38934377/05da2bca-26d8-4955-8379-80c17c8d24fd" alt="Screenshor_1" width="150" height="300">
</div>