# channel

channel用于在字节缓冲区和位于channel另一侧的尸体之间进行有效地传输数据。 nexus for I/O operations

通常情况下channel与os的文件描述符，文件句柄有着一对一的关系，提供维护平台独立性需要的抽象，以最小的开销访问os的io服务。

``` java
    public interface Channel {
        boolean isOpen();
        void close() throws
    }
```

InterruptibleChannel: 可被终端的channel,


FileChannel，文件通道总是阻塞的，不能设置为非阻塞。异步IO，是操作系统的高级特性。

文件空洞 file hole 磁盘上一个文件分配空间小于他的文件大小。 大多数现代文件系统只为实际写入的数据分配磁盘空间。





