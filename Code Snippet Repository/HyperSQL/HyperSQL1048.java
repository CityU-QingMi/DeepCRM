    private boolean renameWithOverwrite(String oldname, String newname) {

        File file = new File(oldname);

        delete(newname);

        boolean renamed = file.renameTo(new File(newname));

        if (renamed) {
            return true;
        }

        System.gc();
        delete(newname);

        if (exists(newname)) {
            new File(newname).renameTo(new File(newDiscardFileName(newname)));
        }

        return file.renameTo(new File(newname));
    }
