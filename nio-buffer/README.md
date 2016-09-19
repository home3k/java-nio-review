# Buffer

Buffer相关的class非常多，最开始也一头雾水，所以首先对其命名进行简单梳理

#### 命名

**[MS][T]Buffer[AM][BO]**

- MS - memory scheme: Heap or Direct.
- T - type: int, short, float, double, char or byte.
- AM - access mode: W writable (default), R read-only.
- BO - byte ordering: S - non-native, U - native.
- B - BigEndian or L - LittleEndian.

classes whose names dont include R, by default are W - writable.

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

0 <= mark <= position <= limit <= capacity.

其实就是一个方便读写操作的数据结构.

flip() clear() rewind() 三个对position, limit的操作

flip: limit=position, position。 基本就是读写切换，进行read操作(from buffer)
clear: position=0, limit=capacity。开启一个新的write(for buffer)
rewind: position=0。 重新进行read。

mark默认-1, undefined.  mark()操作使得mark=position, reset() 使得 position=mark

可以是readonly, 非thread safety.

支持invocation chaining

