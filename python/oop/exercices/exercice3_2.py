class Point:
    def __init__(self , x , y) :
        self.x=x
        self.y=y

class Rectangle: 
    p1 = Point(0, 0)  # Premier coin du rectangle
    p2 = Point(0, 0)  # Deuxième coin du rectangle

    def __init__(self , p1 , p2) :
        self.p1=p1
        self.p2=p2
    def surface(self , a):
        return a**2
            