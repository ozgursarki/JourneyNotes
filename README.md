
# JourneyNotes
<i>Made to deep into Clean Architecture and improve code quality<i>
<br><br>
This is a Note app that user can click to anywhere in the world and leave a not there. 
Whether user is able to see the notes in myNotes screen or see the specific note in the map. 
This application is still in progress..
<br><br>

## Libraries & Tools 🛠️
- [Kotlin](https://kotlinlang.org/docs/home.html)
  - [Flow](https://developer.android.com/kotlin/flow)
  - [Coroutines](https://developer.android.com/kotlin/coroutines)
  - [Balloon](https://github.com/skydoves/Balloon)
- [Jetpack Components](https://developer.android.com/jetpack)
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
    - [Room](https://developer.android.com/training/data-storage/room)
    - [Hilt](https://developer.android.com/training/dependency-injection)
    
## Architecture 
![arcihtecture](https://user-images.githubusercontent.com/109312197/215129481-dfbec2d9-8932-48eb-a0a0-fa04f53326d2.png)

## Package hierarchy
![Screen Shot 2023-01-27 at 19 03 12](https://user-images.githubusercontent.com/109312197/215132642-a2d588b1-74ac-4c2b-8e27-a4ae0b878751.png)

# data
 - local
    
   - This package contains local database related files such as 
        
    -    Converters -> To save reference type property to Room Database ,
        
     -   NoteDao -> Data Access Object - has queries that related to Database ,
        
      -  NoteDatabase -> The Database to store data via Room ,
        
      - NoteEntity -> The Entity that is saved to Database via Room
        
- mapper
	- This package contains mapper for entity model to domain model. By doing this we seperate the network layer from rest of our code
        
	 - NoteMapper -> Maps the NoteEntity to Note class that is used for view
        
 - NoteRepository -> Used for our data related logic such as getting, deleting, inserting Note 

# di
   - Dependency provider functions for our app with Hilt library.

# domain
This layer sits between data and ui layer. By doing this we increase the code quality because our view layer doesn't know anything about how to data saved etc. With this approach we actually coding cleaner.  <br><br>
    
- di 
	- Contains provider functions for use cases
                
 - model
   - this package contains models that is used by view.
            
  - use_case
	   - this package contains our use cases. We can call the use case as a class that has a only one job. By doing this we are separating our logics from viewmodel. Which it gives us more readable, testable and maintainable code.

# ui
- This layer has the view related code such as screens, adapters, viewholders.. and this layer also called presentation layer. 

            
