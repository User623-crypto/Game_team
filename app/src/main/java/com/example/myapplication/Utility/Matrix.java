package com.example.myapplication.Utility;
//Nuk duhet
public class Matrix {

    private float [][] matrix;

    private int rows;
    private int cols;

    public Matrix(int rows,int cols)
    {
        matrix=new float[rows][cols];
        this.rows=rows;
        this.cols=cols;
    }

    public Matrix(float[][] a)
    {

        for (int i=0;i<a.length;i++)
        {
            if(a[i].length!=a[0].length)
            {
                throw new IllegalArgumentException("Every row must have the same number of columns");
            }
        }

        matrix=a;
        rows=a.length;
        cols=a[0].length;
    }

    public Matrix(float[] data,int nRows,int nCols,boolean byRow)
    {
        if(nRows*nCols<data.length)
            throw new IllegalArgumentException("Cant hold all the data values");
        int index=0;
        rows=nRows;
        cols=nCols;
        if(byRow)
        {
            for (int i=0;i<nRows;i++)
            {
                for (int j=0;j<nCols;j++)
                {
                    if(data.length>index)
                    {  matrix[i][j]=data[index];}
                    else
                    {matrix[i][j]=0;}
                    index++;
                }
            }
        }else
        {
            for (int i=0;i<nCols;i++)
            {
                for (int j=0;j<nRows;j++)
                {
                    if(data.length>index)
                    {  matrix[j][i]=data[index];}
                    else
                    {matrix[i][j]=0;}
                    index++;
                }
            }
        }

    }

    public Matrix(float[]data,int Rows,int Cols)
    {
        this(data,Rows,Cols,true);
    }

    public Matrix add(Matrix m) {
        if (!(m.rows() == this.rows && m.cols() == this.cols)) {
            throw new IllegalArgumentException(
                    "The matrices must have the same dimensions.");
        }

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix[i][j] = this.matrix[i][j] + m.get(i, j);
            }
        }

        return this;
    }

    public static Matrix add(Matrix m1, Matrix m2) {
        if (!(m1.rows() == m2.rows() && m1.cols() == m2.cols())) {
            throw new IllegalArgumentException(
                    "The matrices must have the same dimensions.");
        }

        Matrix sum = new Matrix(m1.rows(), m1.cols());

        for (int i = 0; i < m1.rows; i++) {
            for (int j = 0; j < m1.cols; j++) {
                sum.set(m1.get(i, j) + m2.get(i, j), i, j);
            }
        }

        return sum;
    }

    public Matrix sub(Matrix m) {
        if (!(m.rows() == this.rows && m.cols() == this.cols)) {
            throw new IllegalArgumentException(
                    "The matrices must have the same dimensions");
        }

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix[i][j] = this.matrix[i][j] - m.get(i, j);
            }
        }

        return this;
    }

    public static Matrix sub(Matrix m1, Matrix m2) {
        if (!(m1.rows() == m2.rows() && m1.cols() == m2.cols())) {
            throw new IllegalArgumentException(
                    "The matrices must have the same dimensions.");
        }

        Matrix difference = new Matrix(m1.rows(), m1.cols());

        for (int i = 0; i < m1.rows; i++) {
            for (int j = 0; j < m1.cols; j++) {
                difference.set(m1.get(i, j) - m2.get(i, j), i, j);
            }
        }

        return difference;
    }

    public Matrix mult(float scl) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix[i][j] *= scl;
            }
        }

        return this;
    }

    public static Matrix mult(Matrix m, float scl) {
        Matrix mult = Matrix.clone(m);

        for (int i = 0; i < mult.rows(); i++) {
            for (int j = 0; j < mult.cols(); j++) {
                mult.set(mult.get(i, j) * scl, i, j);
            }
        }

        return mult;
    }

    public Matrix mult(Matrix m) {
        if (!(this.cols == m.rows())) {
            throw new IllegalArgumentException(
                    "The number of columns in the first matrix must match the "
                            + "number of rows in the second matrix");
        }

        float[][] matrixArray = new float[this.rows][m.cols()];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < m.cols(); j++) {
                for (int n = 0; n < this.cols; n++) {
                    matrixArray[i][j] += this.get(i, n) * m.get(n, j);
                }
            }
        }

        this.matrix = matrixArray;
        this.cols = m.cols();

        return this;
    }

    public static Matrix mult(Matrix m1, Matrix m2) {
        if (!(m1.cols() == m2.rows())) {
            throw new IllegalArgumentException(
                    "The number of columns in the first matrix must match the "
                            + "number of rows in the second matrix");
        }

        float[][] matrixArray = new float[m1.rows()][m2.cols()];

        for (int i = 0; i < m1.rows(); i++) {
            for (int j = 0; j < m2.cols(); j++) {
                for (int n = 0; n < m1.cols(); n++) {
                    matrixArray[i][j] += m1.get(i, n) * m2.get(n, j);
                }
            }
        }

        return new Matrix(matrixArray);
    }

    public Matrix fill(float val) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix[i][j] = val;
            }
        }

        return this;
    }




    public Matrix copy(Matrix m) {
        this.matrix = Matrix.toArray(m);
        this.rows = m.rows();
        this.cols = m.cols();

        return this;
    }

    public static Matrix clone(Matrix m) {
        return new Matrix(Matrix.toArray(m));
    }

    public static float[][] toArray(Matrix m) {
        float [][] arrCopy = new float[m.rows()][m.cols()];

        for (int i = 0; i < m.rows(); i++) {
            for (int j = 0; j < m.cols(); j++) {
                arrCopy[i][j] = m.get(i, j);
            }
        }

        return arrCopy;
    }



    public void set(float newValue, int rowIndex, int colIndex) {
        this.matrix[rowIndex][colIndex] = newValue;
    }

    public float get(int row,int col)
    {
        return matrix[row][col];
    }
    public int rows()
    {
        return rows;
    }

    public int cols()
    {
        return  cols;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        Matrix m = (Matrix) obj;
        boolean value=true;
        if (this.cols == m.cols() && this.rows == m.rows()) {
            for(int i=0;i<rows;i++)
            {
                for (int j=0;j<cols;j++)
                {
                    if(this.matrix[i][j]!=((Matrix) obj).get(i,j))
                    { value=false; break;}
                }
            }

            return value;
        }

        return false;
    }







}
