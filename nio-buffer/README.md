# Buffer

最开始看`java.nioBuffer`包，相关的class非常多，一头雾水，所以还是先对其命名进行简单梳理。

#### 命名

所有的命名均遵循如下的基本原则。

**\[MS\]\[T\]Buffer\[AM\]\[BO\]**

- MS - memory scheme: Heap or Direct.
- T - type: int, short, float, double, char or byte.
- AM - access mode: W writable (default), R read-only.
- BO - byte ordering: S - non-native, U - native.
- B - BigEndian or L - LittleEndian.

Classes whose names dont include R, by default are W - writable.

所以基本所有的buffer基本都是：

1. [T]Buffer
2. Heap[T]Buffer
3. Heap[T]BufferR
4. Direct[T]Buffer[S|U]
5. Direct[T]BufferR[S|U]
6. ByteBufferAs[T]Buffer[B|L]
7. ByteBufferAs[T]BufferR[B|L]

比如:

ByteBufferAsCharBufferB

```java
    public char get() {
        return Bits.getCharB(bb, ix(nextGetIndex()));
    }

    public char get(int i) {
        return Bits.getCharB(bb, ix(checkIndex(i)));
    }
```

ByteBufferAsCharBufferL

```java
    public char get() {
        return Bits.getCharL(bb, ix(nextGetIndex()));
    }

    public char get(int i) {
        return Bits.getCharL(bb, ix(checkIndex(i)));
    }
```

ByteBufferAsCharBufferRB

```java
    public CharBuffer put(char x) {

        throw new ReadOnlyBufferException();

    }

    public boolean isReadOnly() {
        return true;
    }
```

DirectCharBufferS

```
    public char get() {
        return (Bits.swap(unsafe.getChar(ix(nextGetIndex()))));
    }
```

DirectCharBufferU

```
    public char get() {
        return ((unsafe.getChar(ix(nextGetIndex()))));
    }
```

#### Buffer基础

其实就是一个方便读写操作的数据结构，定义了一些属性，方便操作。

`0 <= mark <= position <= limit <= capacity`

其中flip() clear() rewind() 三个对position, limit的操作

- flip: limit=position, position。 基本就是读写切换，进行read操作(from buffer)
- clear: position=0, limit=capacity。开启一个新的write(for buffer)
- rewind: position=0。 重新进行read。

mark默认-1, undefined.  mark()操作使得mark=position, reset() 使得 position=mark

同事Buffer可以是readonly

它是非thread safety的，特殊情况下，使用需要特别小心。

同时它是支持invocation chaining,

`buffer.flip().position(5).limit(100)`



非boolean类型的所有基本类型都有一个对应的buffer。









