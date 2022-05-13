# How we implemtned our design pattern:
To implement this program we thourghly studied design patterns to ensure that only one instance of the video clip manger would ever be created. We decided to use a Singleton Design Pattern as it ensured only one instance is created and provides a global point of access which we used the method getVideoclips to view the video and delete by object. This was implemented using VideoManager which is responisible of creating only one instance of itself and can be accessed directly through the data feild "instance".

