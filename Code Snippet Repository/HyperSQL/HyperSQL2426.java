    private void processPost(InputStream inStream,
                             String name) throws IOException {

        try {

            // In run() the first line of the requestHeader was already read into rowOut (for a POST
            // this looks something like: 'POST /<some-path> HTTP/1.1')
            //
            // Now let's read the rest of the requestHeader until (including) the blank line that
            // denotes the end of the requestHeader block (We can not assume a fixed number of
            // lines since proxy's might add stuff, nor should we assume a certain order of the
            // requestHeader lines)
            int readLineLength;

            do {
                readLineLength = InOutUtil.readLine(inStream, rowOut);
            } while (readLineLength > 2);    // Blank line is usually 2 bytes ('\r\n')

            // rowOut now contains the entire requestHeader as bytes. It is converted here
            // into a java String, so that we can easily do some error checking on it and
            //  inspect it during debugging
            String requestHeader = iso_8859_1_decoder.decode(
                ByteBuffer.wrap(rowOut.toByteArray())).toString();

// System.out.println(requestHeader); //For debugging
            // Throw an error if the Content-Type is something other than an what
            // ClientConnectionHTTP is supposed to send
            if (requestHeader.indexOf("Content-Type: application/octet-stream")
                    < 0) {
                throw new Exception();
            }

            //TODO: Determine presence of Keep-Alive in requestHeader and act upon it accordingly.
            // if (requestHeader.indexOf("Connection: keep-alive") >= 0) { }
        } catch (Exception e) {
            processError(HttpURLConnection.HTTP_BAD_REQUEST);

            return;
        }

        // inStream's read-pointer will now be positioned at the beginning of the request's pay-load
        processQuery(inStream);
    }
