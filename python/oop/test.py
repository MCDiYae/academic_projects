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
# deletes the attribute age  
