        public N pop()
        {
            while (true)
            {
                Node top = stack.get();
                if (top==null)
                    return null;
                if (stack.compareAndSet(top,top.next))
                {
                    top.next = null;
                    return (N)top;
                }
            }
        }
