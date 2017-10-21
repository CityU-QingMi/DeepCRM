        public String toPath()
        {
            StringBuilder pathlike = new StringBuilder();
            pathlike.append(groupId.replace('.','/'));
            pathlike.append('/').append(artifactId);
            pathlike.append('/').append(version);
            pathlike.append('/').append(artifactId);
            pathlike.append('-').append(version);
            if (classifier != null)
            {
                pathlike.append('-').append(classifier);
            }
            pathlike.append('.').append(type);
            return pathlike.toString();
        }
