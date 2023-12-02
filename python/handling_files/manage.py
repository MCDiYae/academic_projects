file = open("D:/iwa project/python/handling_files/test.txt", "r")
 
# This will print every line one by one in the file
for each in file:
    print (each)


# Python code to illustrate read() mode character wise
print (file.read(5))



# Python code to create a file 'best.txt'
file = open('D:/iwa project/python/handling_files/best.txt','w')
file.write("This is the write command")
file.write("It allows us to write in a particular file")
file.close()
