    public void overlay(Classpath other)
    {
        for (File otherElement : other.elements)
        {
            if (this.elements.contains(otherElement))
            {
                // Skip duplicate entries
                continue;
            }
            this.elements.add(otherElement);
        }
    }
