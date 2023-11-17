class Car:
    def __init__(self,v,m):
        self._vitesse=v
        self.marque=m

    def maMarque(self):
        print("hello i am ",self.marque)   

    def _get_vitesse(self):
        return self._vitesse

    def _set_vitesse(self,v):
        self._vitesse=v
    vitesee=property(_get_vitesse,_set_vitesse)        


y=Car(120,"BMW")     
y.maMarque()
y.vitesee=110
print(y.vitesee)
# print('vitesse',y.vitesse,' marque',y.marque)

class Student:  
    def __init__(self, name, id, age):  
        self.name = name  
        self.id = id  
        self.age = age  
  
    # creates the object of the class Student  
s = Student("John", 101, 22)  
  
# prints the attribute name of the object s  
print(getattr(s, 'name'))  
  
# reset the value of attribute age to 23  
setattr(s, "age", 23)  
  
# prints the modified value of age  
print(getattr(s, 'age'))  
  
# prints true if the student contains the attribute with name id  
  
print(hasattr(s, 'id'))  
# deletes the attribute age  delattr()



# get() and set() method  
  
class Geek: 
    def __init__(self, age = 0): 
         self._age = age 
      
    # getter method 
    def get_age(self): 
        return self._age 
      
    # setter method 
    def set_age(self, x): 
        self._age = x 
  
raj = Geek() 
  
# setting the age using setter 
raj.set_age(21) 
  
# retrieving age using getter 
print(raj.get_age()) 
  
print(raj._age) 


# use of property() function 
  
class Geeks: 
     def __init__(self): 
          self._age = 0
       
     # function to get value of _age 
     def get_age(self): 
         print("getter method called") 
         return self._age 
       
     # function to set value of _age 
     def set_age(self, a): 
         print("setter method called") 
         self._age = a 
  
     # function to delete _age attribute 
     def del_age(self): 
         del self._age 
     
     age = property(get_age, set_age, del_age)  
  
mark = Geeks() 
  
mark.age = 10
  
print(mark.age) 