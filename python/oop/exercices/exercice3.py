import math


class Polygone:
    def __init__(self,points) :
        self.points =points

    def calculer_longueur_contour(self):
        if len(self.points) < 2:
            return 0

        longueur_contour = 0
        for i in range(len(self.points)):
            x1, y1 = self.points[i]
            x2, y2 = self.points[(i + 1) % len(self.points)]
            longueur_contour += math.sqrt((x2 - x1)**2 + (y2 - y1)**2)

        return longueur_contour
    
    def deplacer(self, delta_x, delta_y):
        for i in range(len(self.points)):
            x, y = self.points[i]
            self.points[i] = (x + delta_x, y + delta_y)

# Exemple d'utilisation
points_polygone = [(0, 0), (1, 1), (3, 1), (1, 3)]
polygone = Polygone(points_polygone)

# Afficher les points avant le déplacement
print("Points avant deplacement:", polygone.points)

# Déplacer le polygone
polygone.deplacer(6, 2)

# Afficher les points après le déplacement
print("Points apres deplacement:", polygone.points)            