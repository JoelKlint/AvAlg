%Open file
fileID = fopen('data/wikipedia.txt', 'r');

%Read first row
row = fgetl(fileID);
amountOfVertices = str2double(row);

edges = zeros(amountOfVertices, amountOfVertices);
%Read all edges
row = fgetl(fileID);
while ischar(row)
    row = str2num(row) + 1;
    index = 1;
    while index < size(row, 2)
        edges(row(index), row(index+1)) = edges(row(index), row(index+1)) + 1;
        index = index + 2;
    end
    %Get new row to start next loop
    row = fgetl(fileID);
end
fclose(fileID);

%Start script
alpha = 85/100;
H = zeros(amountOfVertices, amountOfVertices);
D = zeros(amountOfVertices, amountOfVertices);
row_degrees = sum(transpose(edges));
for row = 1:size(edges, 1)
    for col = 1:size(edges, 2)
        if row_degrees(row) == 0
            D(row, col) = 1/amountOfVertices;
        else
            H(row, col) = edges(row, col)/row_degrees(row);
        end
    end
end
p = zeros(1, amountOfVertices);
p(1) = 1;
P = alpha*(H+D) + ((1-alpha)/amountOfVertices)*ones(amountOfVertices);
%iterations = input('How many iterations?\n');

old_prob = p*P;
new_prob = p*P*P;
counter = 1;
while max(abs(old_prob-new_prob)) >= 0.01
    abs(old_prob-new_prob)
    old_prob = new_prob;
    new_prob = new_prob*P;
    counter = counter+1;
end
counter
counter * amountOfVertices^2
%%
prob = p*P^54325;
%find largest elements
max_indexes = double.empty;
max_values = double.empty;
for count = 1:5
    [max_values(end+1),ind]=max(prob(:));
    [m,max_indexes(end+1)]=ind2sub(size(prob),ind);
    prob(max_indexes(end)) = 0;
end
max_indexes
max_values


