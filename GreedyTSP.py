from jpype import startJVM, shutdownJVM, java, addClassPath, JClass, JInt
startJVM(convertStrings=False)
import jpype.imports

try:
  pass
  greedyTSP = JClass('GreedyTSP')
  res = greedyTSP.executionTime(java.lang.Integer(5), java.lang.Integer(50))
  print(res)

except Exception as err:
  print(f"Exception: {err}")