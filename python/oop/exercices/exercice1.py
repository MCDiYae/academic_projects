import math


class Point2D:
    def __init__(self,a,b):
        self.a = a
        self.b = b
    def afficher(self):
        print('la point  :( ', self.a ,',', self.b ,')')
    def deplacer(self,dx,dy):
        self.a=self.a+dx
        self.b=self.b+dy
    def distance(self, second_point):
        dx=self.a -second_point.a
        dy=self.b -second_point.b
        distance = math.sqrt(dx**2 + dy**2)
        return distance
    
class Point3D(Point2D):
    def __init__(self, a, b,z):
        super().__init__(a, b)
        self.z=z

    def afficher(self):
        print ('point 3d : (', self.a ,',' , self.b , ',' ,self.z ,')' )

    def deplacer(self, dx, dy ,dz):
        super().deplacer(dx, dy)   
        self.z +=dz
    def distance(self, second_point):  
        dx=self.a -second_point.a
        dy=self.b -second_point.b
        dz = self.z - second_point.z
        distance = math.sqrt(dx**2+dy**2+dz**2)
        return distance

# Exemple d'utilisation
point1 = Point2D(1, 2)
point2 = Point2D(4, 6)

point1.afficher()  # Affiche: Point2D: (1, 2)

point1.deplacer(2, 3)
point1.afficher()  # Affiche: Point2D: (3, 5)

dist = point1.distance(point2)
print(f"La distance entre point1 et point2 est : {dist}")
#POINT 3D
point3D = Point3D(3, 4, 5)
point3D.deplacer(1, 1, 1)
point3D.afficher()
ds=point3D.distance(Point3D(1, 2, 3))
print(f"La distance entre point1 et point2 et point 3 est : {ds} ")