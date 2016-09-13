%Open file
%fileID = fopen('data/pw09_100.9.txt', 'r');
fileID = fopen('data/matching_1000.txt', 'r');

%Read first row
row = fgets(fileID);
%Convert first row to digits
row = textscan(row, '%d %d');
row = cell2mat(row);
amountOfNodes = row(1);
amountOfEdges = row(2);

%Create matrix of all edges
edges = zeros(amountOfNodes, amountOfNodes);

row = fgets(fileID);
while ischar(row)
    row = textscan(row, '%d %d %d');
    row = cell2mat(row);
    node1 = row(1);
    node2 = row(2);
    weight = row(3);
    edges(node1, node2) = weight;
    edges(node2, node1) = weight;
    row = fgets(fileID);
end
fclose(fileID);

%Run algorithm
iterations = 100;
answer = zeros(1,iterations);
for i = 1:iterations
    %O(n)
    subset = randi([0 1], 1, amountOfNodes);
    %vector*matrix => O(n^2)
    columnweights = subset*edges;
    otherSubSet = 1-subset;
    columnweights = transpose(columnweights);
    answer(i) = otherSubSet * columnweights;
end