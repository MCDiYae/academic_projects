def egal(L,M):
    if len(L)!=len(M):
        return False
    for i in range(len(L)):
        if L[i]!=M[i]:
            return False
    return True

f=[1,3,45,56,6,5]
d=[1,3,45,56,6,5]    
print('resultat ',egal(f,d))


