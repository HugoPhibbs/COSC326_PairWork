package video_manager;

/**
 * Class for a VideoClip
 *
 * Pretty much implemented just for VideoManager
 */
public class VideoClip {

    Image previewImage;
    int startFrame;
    int endFrame;

    float fps;
    String videoFileName;
    String videoDescription;

    VideoClip() {

    }

    VideoClip(String videoName) {
        videoFileName = videoName;
    }
}
