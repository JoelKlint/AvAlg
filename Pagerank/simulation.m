%Open file
fileID = fopen('data/tiny.txt', 'r');

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
iterations = input('How many iterations?\n');
alpha = 85/100;
current_vertice = 1;
result = zeros(1, amountOfVertices);
result(current_vertice) = result(current_vertice) + 1;
old_res = result;
old_res(current_vertice) = 1000;
counter = 1;

%for iteration = 1:iterations
while max(abs(1./old_res-1./result)) >= 0.000001
    counter = counter+1;
    walk_or_jump = rand;
    if walk_or_jump > alpha
        %jump
        new_vertice = randi([1 amountOfVertices]);
        current_vertice = new_vertice;
        result(current_vertice) = result(current_vertice) + 1;
    else
        %walk
        current_edges = edges(current_vertice,:);
        if sum(current_edges) == 0 
            %jump
            new_vertice = randi([1 amountOfVertices]);
            current_vertice = new_vertice;
            
            old_res = result;
            
            result(current_vertice) = result(current_vertice) + 1;
        else
            %build propability vector
            prob_vector = double.empty;
            for edge = 1:size(current_edges, 2)
                if current_edges(edge) > 0
                    for count = 1:current_edges(edge)
                        prob_vector(end+1) = edge;
                    end
                end
            end
            %decide which vertice to walk to
            new_vertice_index = randi([1 size(prob_vector, 2)]);
            new_vertice = prob_vector(new_vertice_index);
            current_vertice = new_vertice;
            
            old_res = result;
            
            result(current_vertice) = result(current_vertice) + 1;
        end
    end    
end
% 1./old_res
% 1./result
counter
