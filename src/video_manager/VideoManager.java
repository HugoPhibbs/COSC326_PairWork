package video_manager;

import java.util.ArrayList;

/**
 * VideoManager class
 * <p>
 * Code for enforcing a single instance is used from:
 * https://stackoverflow.com/questions/27782769/how-to-make-sure-that-there-is-just-one-instance-of-class-in-jvm
 * <p>
 * // TODO test that the single instance thing works!
 */
public class VideoManager {

    private static final VideoManager instance = new VideoManager();
    /**
     * ArrayList to hold VideoClips that this VideoManager has
     */
    private ArrayList<VideoClip> videoClips = new ArrayList<>();

    /**
     * Private constructor for a VideoManager
     *
     */
    private VideoManager() {
    }

    static {
    }

    /**
     * Gets the single instance of this VideoManager
     *
     * @return VideoManager class for this instance
     */
    public static VideoManager getInstance() {
        return instance;
    }

    public ArrayList<VideoClip> getVideoClips() {
        return videoClips;
    }

    /**
     * Adds a VideoClip to the VideoManager object
     *
     * @param videoClip VideoClip object to be added
     */
    public void addVideoClip(VideoClip videoClip) {
    }

    /**
     * Removes a VideoClip from this VideoManager
     *
     * @param videoClip VideoClip to be deleted
     * @return boolean if the inputted videoClip was deleted, false if it wasn't found and deleted
     */
    public boolean removeVideoClip(VideoClip videoClip) {
        return true;
    }
}
