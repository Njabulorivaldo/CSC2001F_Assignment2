import os
import matplotlib.pyplot as plt
import numpy as np
import plotly.graph_objects as go



random_num = []

x_values = []
best_case = []
avg_case = []
worst_case = []

def submitRandoms(start, stop, step) :

	for i in range(start, stop, step):
		os.system("java -cp bin AVLExperiment "+str(i))

def plotGraph(name):
	
	y = np.array([worst_case])
	plt.plot(x_values, best_case, label = "Best Case")
	plt.plot(x_values, avg_case, label = "Average Case")
	plt.plot(x_values, worst_case, label = "Worst Case")
	
	plt.xlabel("No_Of_Randomisations")
	plt.ylabel("No_Of_Operations")
	plt.legend()
	plt.ylim([0, 18])
	plt.show()
	plt.savefig("./log/"+name+"_graph.png")
	
	fig = go.Figure(data=[go.Table(header=dict(values=['Randomisations', 'Best Case', 'Average Case', 'Worst Case']),
                 cells=dict(values=[x_values, best_case, avg_case, worst_case]))
                     ])
	fig.show()
	
	
	x_values.clear()
	best_case.clear()
	avg_case.clear()
	worst_case.clear()
	



def insertGraph():
	
	with open("FinalResults_data.txt", "r") as insert_data:
		for i in insert_data.readlines():
			parts = i.split(",")
			
			x_values.append(parts[0])
			best_case.append(int(parts[1]))
			worst_case.append(int(parts[2]))
			avg_case.append(float(parts[3]))
		insert_data.close()
	
	plt.title("Inserts Graph")
	plotGraph("Insert")
	
			
def findGraph():
	
	with open("FinalResults_data.txt", "r") as find_data:
		for i in find_data.readlines():
			parts = i.split(",")
			
			x_values.append(parts[0])
			best_case.append(int(parts[4]))
			worst_case.append(int(parts[5]))
			avg_case.append(float(parts[6]))
		find_data.close()
	
	plt.title("Finds Graph")
	plotGraph("Find")


if __name__ == "__main__":
	
	try:
		start = 495
		stop = 10415
		step = 496
		submitRandoms(start, stop, step)
		insertGraph()
		findGraph()
	except:
		pass
	
