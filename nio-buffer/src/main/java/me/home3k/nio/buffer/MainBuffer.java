package me.home3k.nio.buffer;

/**
 * @author home3k
 */
public class MainBuffer {

    public static void main(String[] args) {
        MainBuffer mainBuffer = MainBuffer.me();
    }

    private MainBuffer() {
    }

    public static MainBuffer me() {
        MainBuffer mainBuffer = new MainBuffer();
        return mainBuffer;
    }

}
