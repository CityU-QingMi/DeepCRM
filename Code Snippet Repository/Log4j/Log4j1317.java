    @Override
    public synchronized void flush() throws IOException {
        try {
            if (this.data != null && this.datagramSocket != null && this.inetAddress != null) {
                if (footer != null) {
                    copy(footer, 0, footer.length);
                }
                final DatagramPacket packet = new DatagramPacket(data, data.length, inetAddress, port);
                datagramSocket.send(packet);
            }
        } finally {
            data = null;
            if (header != null) {
                copy(header, 0, header.length);
            }
        }
    }
