class Calcul:
    # consturcteur par defaut
    def __init__(self) -> None:
        pass

    def factorial(self,n):  
        if(n==1 or n==0):
            return 1;
        else :
            return n*self.factorial(n-1);

    def sommation(self ,n):
        return n*(n+1)/2    
    

    def Multiplication(self,a,b):  
        return a*b;
    def TableMult(self,a):  
        for i in range (1,11):
            print(a,"*", i ,"=",a*i)
        

    def Division (self,a,b):  
        return a/b;


    def factorielle(self , n):
        f=1
        for i in range (2,n+1):
            f=f*i
        return f    

    def nbrPremier(self,n):
        if n <= 1:
            return False
        for i in range (2, n ):
            if n%i==0:
                return False
            
        return True  
      
    @staticmethod
    def listDiv(n):
        diviseurs=[]
        for i in range ( 1,n ):
            if n%i==0:
                diviseurs.append(i)
        return diviseurs
            


obj= Calcul()
fact=obj.factorial(3)
fact2=obj.factorielle(5)
suite=obj.sommation(10)
prm=obj.nbrPremier(211)
prm2=obj.nbrPremier(1069)
prm3=obj.nbrPremier(9601)
mult=obj.Multiplication(4,8)
tab=obj.TableMult(4)
print(fact)
print(suite)
print(prm)
print(tab)

# appel static method
res = Calcul.listDiv(33)
print('liste des diviseur 33 ',res )