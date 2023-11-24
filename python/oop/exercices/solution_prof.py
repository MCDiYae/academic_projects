import math

math
class Point:
    def __init__(self,x,y):
        self.x=x
        self.y=y

class Vecteur:
     def __init__(self, P1,P2):
         self.A=P1
         self.B=P2

     def coordonnees(self):
         return {'X': self.B.x-self.A.x, 'Y':self.B.y-self.A.y }

     def inverser(self):
         self.A,self.B=self.B,self.A
     def longueur(self):
         return math.sqrt( (self.B.x-self.A.x) **2+(self.B.y-self.A.y) **2)
     def angleAxeX(self):
        return math.atan((self.B.x-self.A.x)/(self.B.y-self.A.y))
     def etaler(self,d):
         dx=math.cos(self.angleAxeX())*d
         dy=math.sin(self.angleAxeX())*d
         self.B.x+=dx
         self.B.y+=dy


A=Point(1,1)
B=Point(3,3)
v=Vecteur(A,B)

print(v.coordonnees())

print("la longuer du vecteur est :",v.longueur())
print("l'angele avec l'axe des X est ::",v.angleAxeX())
v.etaler(2)
print("la longuer du vecteur est :",v.longueur())

class Point2D:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    # La fonction afficher() qui permet d'afficher les corrdonnes de point 2D.
    def afficher(self):
        print("x: ", self.x, " y: ", self.y,end=" ")
    # La fonction deplacer
    def deplacer(self, dx, dy):
        self.x += dx
        self.y += dy

    def distance(self,P):
        return math.sqrt( (P.x-self.x)**2+(P.y-self.y)**2 )

class Point3D (Point2D):
    def __init__(self,x,y,z):
        super().__init__(x,y)
        self.z=z
    def afficher(self):
        super().afficher()
        print(" z: ",self.z)
    def deplacer(self, dx, dy, dz):
        super().deplacer(dx,dy)
        self.z+=dz
    def distance(self,P):
        return math.sqrt( (P.x-self.x)**2+(P.y-self.y)**2+(P.z-self.z))

P1=Point3D(1,1,1)
P2=Point3D(2,3,4)
P1.afficher()
P1.afficher()
print("la distance est :",P1.distance(P2))