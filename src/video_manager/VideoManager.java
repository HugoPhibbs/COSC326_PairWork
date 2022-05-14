package video_manager;

import java.util.ArrayList;

/**
 * VideoManager class
 * <p>
 * Code for enforcing a single instance is used from:
 * https://stackoverflow.com/questions/27782769/how-to-make-sure-that-there-is-just-one-instance-of-class-in-jvm
 * <p>
 */
public class VideoManager {

    /**
     * The single instance of this VideoManager
     */
    private static final VideoManager instance = new VideoManager();

    /**
     * ArrayList to hold VideoClips that this VideoManager has
     */
    private final ArrayList<VideoClip> videoClips = new ArrayList<>();

    /**
     * Private constructor for a VideoManager
     */
    private VideoManager() {
    }

    static {
    }

    /**
     * Returns the VideoClips that this VideoManager has
     *
     * @return ArrayList containg VideoClips as described
     */
    public static ArrayList<VideoClip> getVideoClips() {
        return instance.videoClips;
    }

    /**
     * Adds a VideoClip to the VideoManager object
     *
     * @param videoClip VideoClip object to be added
     */
    public static void addVideoClip(VideoClip videoClip) {
        instance.videoClips.add(videoClip);
    }

    /**
     * Removes a VideoClip from this VideoManager
     *
     * @param videoClip VideoClip to be deleted
     * @return boolean if the inputted videoClip was deleted, false if it wasn't found and deleted
     */
    public boolean removeVideoClip(VideoClip videoClip) {
        instance.videoClips.remove(videoClip);
        return true;
    }
}
