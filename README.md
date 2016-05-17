# Clean-Android
This App is a example for a clean Android App. It followes a Model-View-Presenter pattern. The Model layer is splitted into Data and Domain which are both Java Modules. In the Java modules everything can be Unit tested. Dependency injection is done by **Dagger2**.

## Data
As the name intends the data layers purpose is to provide access to data. This is the lowest layer. Access to data is provided by one ore more DataSource. A DataSource is an interface which defines access to some data. A DataSource can have several implementations which defines where the data comes from. This App has a `WeatherDataSource` interface and a `WeatherApiDataSource` which implements that one. There can be any other implementations like Database, Caching or a Dummy for testing. 

The access to the API is implemented using **Retrofit**. The `WeatherService` returns all data as `rx.Observable`. The response data, called entities are implemented using **AutoValue**. This way all the boilerplate gets generated and keeps the class clean and simple. The conversion from and to JSON is done by a `GsonConverterFactory` within the Retrofit configuration.

## Domain
The Domain is responsible to provide access to the Data and to execute the access in background. All this is done within a UseCase. A UseCase should only serve one purpose. For example it could get some data from a DataSource. The basic things are handled in the abstract UseCase class. It provides a subscribe method which takes an `rx.Observable` to subscribe on. It subscribes on an IO Scheduler and returns  the data on Androids Main Thread. When you call `subscribe()` on the abstract UseCase class it expects a `DefaultSubscriber`. This Subscriber handles the general errors like `HttpExceptions` and delegates them to the `ErrorCallback` methods `onGenericError()` or `onServerError()`. Like `onError()`, `onNext()` and `onComplete()` are implemented as well but don’t do anything. So in your UseCase implementation you are not forced to implement. So all the general errors are handled for you you only need to focus to handle the success and what to do in case an error happened (disable button, reset UI etc.) So each UseCase should define a Callback which extends from `UseCase.ErrorCallback` like in the `GetWeather` example UseCase. 

A UseCase is not a Singleton but each Presenter should have only one of a specific type. But it can be executed several times. To stop all ongoing running operations in background RxJava provides a CompositeSubscription. Each subscription that is made is added to it. So a UseCase can cancel them all at once by calling `cancelAll()`.

If you need to convert the data to ViewModels you can pass a Transformer. This way the Domain layer can convert the data without knowing the higher App layer.

## App
The App layer contains the Views like Activity and Fragments, the Presenter and all other Android stuff. 

Each Activity or Fragment should implement a View Interface provided by the corresponding Presenter. The Presenter is defined as a generic type passed to the BaseActivity. It gets injected from the BaseActivity. I think the Presenter should be free of Android lifecycle methods. So it only provides methods like `attachView()`, `detachView()` and other setup or cleanup methods. The BaseActivity takes care to call the correct methods at the right time. When the Activity gets destroyed it calls `detachView()` on its presenter, so in case anything is still going on in background there should be no view leaks. To stop all work in background that was executed on a UseCase the BaseActivity also calls `cancelAllUseCases()`. This will stop any ongoing work in background. 

In best case the Presenter can be JUnit tested. But if you have references to Android you can use **Robolectric** to Unit test it. The actual UI can be tested using **Espresso**.

## References
[1] http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/<br />
[2] http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/<br />
[3] http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html<br />
[4] http://square.github.io/retrofit/<br />
[5] https://github.com/google/auto/tree/master/value<br />
[6] https://github.com/ReactiveX/RxJava/wiki<br />
