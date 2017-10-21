        @SuppressWarnings("")
        @Override
        public SocketAppender build() {
            boolean immediateFlush = isImmediateFlush();
            final boolean bufferedIo = isBufferedIo();
            final Layout<? extends Serializable> layout = getLayout();
            if (layout == null) {
                AbstractLifeCycle.LOGGER.error("No layout provided for SocketAppender");
                return null;
            }

            final String name = getName();
            if (name == null) {
                AbstractLifeCycle.LOGGER.error("No name provided for SocketAppender");
                return null;
            }

            final Protocol protocol = getProtocol();
            final Protocol actualProtocol = protocol != null ? protocol : Protocol.TCP;
            if (actualProtocol == Protocol.UDP) {
                immediateFlush = true;
            }

            final AbstractSocketManager manager = SocketAppender.createSocketManager(name, actualProtocol, getHost(), getPort(),
                    getConnectTimeoutMillis(), getSslConfiguration(), getReconnectDelayMillis(), getImmediateFail(), layout, getBufferSize(), getSocketOptions());

            return new SocketAppender(name, layout, getFilter(), manager, isIgnoreExceptions(),
                    !bufferedIo || immediateFlush, getAdvertise() ? getConfiguration().getAdvertiser() : null);
        }
