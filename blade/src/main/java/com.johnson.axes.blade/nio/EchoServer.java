/*
 * Copyright 2014 Future TV, Inc.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */
package com.johnson.axes.blade.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2014/02/14
 * Time: 17:18
 */
public class EchoServer extends Thread {

    private final SocketChannel socket;
    private static final int PORT = 20000;

    public EchoServer(SocketChannel socket) {
        this.socket = socket;
    }

    public static void startServer() throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(PORT);
        channel.socket().bind(address);
        while (true) {
            SocketChannel socket = channel.accept();
            new EchoServer(socket).start();
        }
    }

    public void run() {
        nonBlockingMode();
    }

    private void nonBlockingMode() {
        Selector selector = null;
        try {
            selector = Selector.open();
            socket.configureBlocking(false);
            socket.socket().setOOBInline(true);
            socket.register(selector, SelectionKey.OP_READ);
            for (int i=0; true; i++) {
                int selNum = selector.select();
                if (selNum > 0) {
                    for (SelectionKey key : selector.selectedKeys()) {
                        System.out.println("selected ops = " + key.interestOps());
                    }
                }
//                selector.selectedKeys().clear();
                ByteBuffer buf = ByteBuffer.allocate(1);
                int len = socket.read(buf);
                if (len < 0) {
                    break;
                } else if (len > 0) {
                    buf.flip();
                    System.out.println(i + "\treceive byte value: " + buf.get());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Client {
        public static void main(String[] args) throws IOException {
            Socket socket = new Socket();
            socket.setOOBInline(true);
            socket.connect(new InetSocketAddress("localhost", PORT));
            try {
                for (int i=0; i<10; i++) {
                    socket.sendUrgentData(i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                socket.close();
            }

        }
    }
}
