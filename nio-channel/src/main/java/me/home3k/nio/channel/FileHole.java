package me.home3k.nio.channel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author home3k
 */
public class FileHole {

    public static void main(String[] args) throws IOException {
        File tempFile = File.createTempFile("holy", null);
        RandomAccessFile file = new RandomAccessFile(tempFile, "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(100);
        put(0, buffer, channel);
        put(5000000, buffer, channel);
        put(50000, buffer, channel);

        System.out.println("Wrote temp file '" + tempFile.getPath() + "', size = " + channel.size());
        channel.close();
        file.close();
    }

    private static void put(int position, ByteBuffer buffer, FileChannel channel) throws IOException {
        String string = "*<-- location" + position;
        buffer.clear();
        buffer.put(string.getBytes());
        buffer.flip();
        channel.position(position);
        channel.write(buffer);
    }
}
