    public void write() throws IOException, TarMalformatException {
        if (TarFileOutputStream.debug) {
            System.out.println(
                RB.write_queue_report.getString(entryQueue.size()));
        }

        TarEntrySupplicant entry;

        try {
            for (int i = 0; i < entryQueue.size(); i++) {
                System.err.print(Integer.toString(i + 1) + " / "
                                 + entryQueue.size() + ' ');
                entry = entryQueue.get(i);
                System.err.print(entry.getPath() + "... ");
                entry.write();
                archive.assertAtBlockBoundary();
                System.err.println();
            }

            archive.finish();
        } catch (IOException ioe) {
            System.err.println();    // Exception should cause a report

            try {

                // Just release resources from any Entry's input, which may be
                // left open.
                for (TarEntrySupplicant sup : entryQueue) {
                    sup.close();
                }

                archive.close();
            } catch (IOException ne) {

                // Too difficult to report every single error.
                // More important that the user know about the original Exc.
            }

            throw ioe;
        }
    }
