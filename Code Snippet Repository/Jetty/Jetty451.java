        private Part(String name, String fileName, String contentType, ContentProvider content, HttpFields fields)
        {
            this.name = name;
            this.fileName = fileName;
            this.contentType = contentType;
            this.content = content;
            this.fields = fields;
            this.headers = headers();
            this.length = content.getLength() < 0 ? -1 : headers.remaining() + content.getLength();
        }
