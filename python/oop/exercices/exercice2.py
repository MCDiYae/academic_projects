import math

class Point:
    def __init__(self, x=0, y=0):
        self.x = x
        self.y = y

class Vecteur:
    def __init__(self, point1, point2):
        self.p1 = point1
        self.p2 = point2

    def coordonnees(self):
        return {"x": self.p2.x - self.p1.x, "y": self.p2.y - self.p1.y}
    def inverser(self):
        self.p1, self.p2 = self.p2, self.p1

    def norme(self):
        dx= self.p2.x - self.p1.x
        dy= self.p2.y - self.p1.y
        norme=math.sqrt(dx**2+dy**2) 
        return norme
    def angle_x(self):
        dx = self.p2.x - self.p1.x
        dy = self.p2.y - self.p1.y
        return math.degrees(math.atan2(dy, dx))
    def etaler(self, d):
        ratio = d / self.longueur()
        self.p2.x = self.p1.x + ratio * (self.p2.x - self.p1.x)
        self.p2.y = self.p1.y + ratio * (self.p2.y - self.p1.y)

point1 = Point(1, 2)
point2 = Point(4, 6)
vecteur = Vecteur(point1, point2)

print("Coordonnées du vecteur:", vecteur.coordonnees())    
print("norme du vecteur:", vecteur.norme())  
print("Angle entre le vecteur et l'axe X:", vecteur.angle_x())
vecteur.inverser()
print("Coordonnées du vecteur après inversion:", vecteur.coordonnees())