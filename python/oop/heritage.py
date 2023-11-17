class Animal:  
    def speak(self):  
        print("Animal Speaking")

#heritage 'extend' just ()
class Dog(Animal):  
    def bark(self):  
        print("hawhawhaw")  
d = Dog()  
d.bark()  
d.speak()  

# Multi-Level inheritance
class DogChild(Dog):  
    def eat(self):  
        print("Eating bread...")  

e=DogChild()
e.speak()  
e.eat()      

# Multiple inheritance
