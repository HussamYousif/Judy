import os 
from pycparser import c_parser, parse_file, c_generator


f = "./"

def getFiles(fileDirectory):
    fileList = []

    for f in os.listdir(fileDirectory):
        if f.endswith(".cpp"):
            fileList.append(f)
    return fileList

def parseFile(inputFile):
    ast = parse_file(inputFile, use_cpp=True)
    generator = c_generator.CGenerator()
    print(generator.visit(ast))

fileList = getFiles(f)

for f in fileList:
    print ("parsing " + f)
    parseFile(f)



