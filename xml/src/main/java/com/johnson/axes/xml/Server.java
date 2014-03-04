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
package com.johnson.axes.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2014/01/19
 * Time: 11:19
 */
public class Server {

    private ServerSocket socket;

    private ThreadLocal<Socket> sockets = new ThreadLocal<Socket>();

    private void bind(int port) throws IOException {
        socket = new ServerSocket(port);
    }

    private Socket accept() throws IOException {
        return socket.accept();
    }

    public void start() throws IOException {
        Socket s;
        while ((s=accept()) != null) {
            final Socket f = s;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sockets.set(f);
                    byte[] buffer = new byte[1024];
                    try {
                        InputStream in = f.getInputStream();
                        OutputStream out = f.getOutputStream();
                        while (true) {
                            int len = in.read(buffer);
                            if (len > 0) {
                                process(new String(buffer, 0, len));
                                out.write(buffer, 0, len);
                                out.flush();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                private void process(String line) {
                    System.out.println(line);
                }
            }).start();
        }
    }

    public void stop() {}

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.bind(10000);
        server.start();
    }
}
