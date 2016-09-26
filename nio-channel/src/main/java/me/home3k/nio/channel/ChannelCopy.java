package me.home3k.nio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author home3k
 */
public class ChannelCopy {

    public static void main(String[] args) throws IOException {
        copy(Channels.newChannel(System.in), Channels.newChannel(System.out));
    }

    private static void copy(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (src.read(buffer) != -1) {
                buffer.flip();
                dest.write(buffer);
                buffer.clear();
            }
        } finally {
            src.close();
            dest.close();
        }
    }

    private static void copy1(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (src.read(buffer) != -1) {
                buffer.flip();
                dest.write(buffer);
                buffer.compact();
            }
            buffer.flip();
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
        } finally {
            src.close();
            dest.close();
        }
    }

}
