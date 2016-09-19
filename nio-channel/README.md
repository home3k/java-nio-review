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