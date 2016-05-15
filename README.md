# android-mvp
This App is a example how to separte an Android App into layers. The aim is to make as much Unit testable as possible.

## Model-View-Presenter + Data & Domain
The App follows the Model-View-Presenter (MVP) design. The Model layer is plitted into Data and Domain. 

### Data
As the name intends the data layers purpose is to provide access to data. Therefore it provides DataSource. A DataSource is an interface which defines access to some data. A DataSource can have several implementations which defines where the data comes from. In this App there is a WeatherDataSource interface and a WeatherApiDataSource which implements that one. There can be any other implementations for WeatherDataSource like Database, Caching or a Dummy for testing. The access to the API is implemented using Retrofit. The WeatherService returns all data as `rx.Observable`. 

### Domain
The Domain is responsible to provide access to the Data and to execute the access in background. All this is done within a UseCase. A UseCase is intended to provide access to one type of data. It provides an execute method which takes the paramters and if needed a Callback. The abstract `UseCase` provides a subscribe method which takes an `rx.Observable` to subscribe on. It subscribes on an IO Scheduler and returns on Androids Main Thread.

