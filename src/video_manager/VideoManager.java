package video_manager;

/**
 * VideoManager class
 *
 * Code for enforcing a single instance is used from:
 * https://stackoverflow.com/questions/27782769/how-to-make-sure-that-there-is-just-one-instance-of-class-in-jvm
 *
 */
public class VideoManager {

    private static VideoManager instance;

    /**
     * Private constructor for a VideoManager
     *
     */
    private VideoManager() {

    }

    static {
        instance = new VideoManager();
    }

    /**
     * Gets the single instance of this VideoManager
     *
     * @return VideoManager class for this instance
     */
    public static VideoManager getInstance() {
        return instance;
    }
}
