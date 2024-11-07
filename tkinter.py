from tkinter import *

root = Tk()

root.geometry('500x500')
root.title("Registration form")

label = Label(root, text="Registration Form", width=20, font=("bold", 10))
label.place(x=90, y=53)

label_1 = Label(root, text="Enter Name", width=20, font=("bold", 10))
label_1.place(x=80, y=130)
entry1 = Entry(root)
entry1.place(x=240, y=130)

label_2 = Label(root, text="Email: ", width=20, font=("bold", 10))
label_2.place(x=68, y=180)
entry2 = Entry(root)
entry2.place(x=240, y=180)

label_3 = Label(root, text="Gender:", width=20, font=("bold", 10))
label_3.place(x=70, y=230)
var = IntVar()
Radiobutton(root, text="Male", padx=20, variable=var, value=1).place(x=235, y=230)
Radiobutton(root, text="Female", padx=20, variable=var, value=2).place(x=290, y=230)

label_4 = Label(root, text="Age: ", width=20, font=("bold", 10))
label_4.place(x=70, y=280)

entry_3 = Entry(root)
entry_3.place(x=240, y=280)

Button(root, text="SUBMIT", width=20, bg="red", fg="white", font=("bold", 10)).place(x=180, y=380)

root.mainloop()
