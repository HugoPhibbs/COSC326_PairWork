# VideoManager

*By Hugo Phibbs and Ben Forde*

## Our Approach

- We created VideoManager as practically a static class, with a simple interface that users can use to add and remove
  VideoClips.

### Example usage

- Bellow is a code fragment on how to use our VideoManager class.

- To add a ````VideoClip```` object to the VideoManager, use VideoManager such as:

```
VideoManager.addVideoClip(new VideoClip());
```

- Then to remove a ````VideoClip```` object from the VideoManager, use VideoManager such as:

```
VideoManager.removeVideoClip(new VideoClip());
```

- Any ````VideoClip```` objects that are added can be access statically using:

```
VideoManager.getVideoClips();
```

## Design patterns

- We consider our approach to use two main design patterns, the Facade and Singleton design principles.
- We can explain the general structure of the VideoManager class with the Facade design pattern. This is due to the
  VideoManager hiding the complexity of its logic, providing a simple interface for users. This not only applies to how
  users interact with VideoManager only statically, but also specifically with methods ````addVideoClip(VideoClip)````
  and ```removeVideoClip(VideoClip)```. We decided on this design pattern to make it easy for possible users to add and
  remove VideoClips, minimising the chance that the VideoManager class is misused.
- // TODO: https://refactoring.guru/design-patterns/singleton

## Possible challenges

## Sources

- https://refactoring.guru/design-patterns/facade
- https://stackoverflow.com/questions/27782769/how-to-make-sure-that-there-is-just-one-instance-of-class-in-jvm