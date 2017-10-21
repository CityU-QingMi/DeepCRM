    public void myPrepare() {

        if (SIZE_FILTER_TYPES == null) {

            SIZE_FILTER_TYPES = Arrays.asList(new KeyValueObject(
                    "mediaFileView.gt", getText("mediaFileView.gt")),
                    new KeyValueObject("mediaFileView.ge",
                            getText("mediaFileView.ge")), new KeyValueObject(
                            "mediaFileView.eq", getText("mediaFileView.eq")),
                    new KeyValueObject("mediaFileView.le",
                            getText("mediaFileView.le")), new KeyValueObject(
                            "mediaFileView.lt", getText("mediaFileView.lt")));

            FILE_TYPES = Arrays.asList(new KeyValueObject("mediaFileView.any",
                    getText("mediaFileView.any")), new KeyValueObject(
                    "mediaFileView.others", getText("mediaFileView.others")),
                    new KeyValueObject("mediaFileView.image",
                            getText("mediaFileView.image")),
                    new KeyValueObject("mediaFileView.video",
                            getText("mediaFileView.video")),
                    new KeyValueObject("mediaFileView.audio",
                            getText("mediaFileView.audio")));

            SIZE_UNITS = Arrays.asList(new KeyValueObject(
                    "mediaFileView.bytes", getText("mediaFileView.bytes")),
                    new KeyValueObject("mediaFileView.kb",
                            getText("mediaFileView.kb")), new KeyValueObject(
                            "mediaFileView.mb", getText("mediaFileView.mb")));

            SORT_OPTIONS = Arrays.asList(new KeyValueObject("name",
                    getText("generic.name")), new KeyValueObject(
                    "date_uploaded", getText("mediaFileView.date")),
                    new KeyValueObject("type", getText("mediaFileView.type")));
        }

        refreshAllDirectories();
    }
