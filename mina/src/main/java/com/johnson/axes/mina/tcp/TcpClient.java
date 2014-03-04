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
package com.johnson.axes.mina.tcp;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * Created by Johnson.Liu
 * <p/>
 * Author: Johnson.Liu
 * Date: 2014/02/19
 * Time: 16:34
 */
public class TcpClient {

    private static final long CONNECT_TIMEOUT = 60 * 1000;
    private static final boolean USE_CUSTOM_CODEC = false;

    public static void main(String[] args) {
        SocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);

        if (USE_CUSTOM_CODEC) {
//            connector.getFilterChain().addLast("codec",
//                    new ProtocolCodecFilter(new SumUpProtocolCodecFactory(false)));
        } else {
            connector.getFilterChain().addLast("codec",
                    new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        }
        connector.getFilterChain().addLast("logger", new LoggingFilter());
//        connector.setHandler(new ClientSessionHandler(new int[] {1}));
        IoSession session;

//        while (true) {
//            try {
//
//            } catch (RuntimeIoException e) {
//                System.err.println("Failed to connect.");
//                e.printStackTrace();
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        }
//        session.getCloseFuture().awaitUninterruptibly();
        connector.dispose();
    }
}
