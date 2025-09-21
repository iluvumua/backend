-- Fix Equipment-Meter Associations
-- This script updates equipment records to properly associate them with meters
-- based on the meter description containing the equipment name

-- Update equipment with meter_id based on meter description matching equipment name
UPDATE equipment
SET meter_id = (
    SELECT MIN(m.id)
    FROM meter m
    WHERE m.description LIKE '%' + equipment.name + '%'
)
WHERE meter_id IS NULL
AND EXISTS (
    SELECT 1 FROM meter m
    WHERE m.description LIKE '%' + equipment.name + '%'
);

-- Alternative approach: Update based on designation if name doesn't match
UPDATE equipment
SET meter_id = (
    SELECT MIN(m.id)
    FROM meter m
    WHERE m.description LIKE '%' + equipment.designation + '%'
)
WHERE meter_id IS NULL
AND designation IS NOT NULL
AND EXISTS (
    SELECT 1 FROM meter m
    WHERE m.description LIKE '%' + equipment.designation + '%'
);

-- Verify the associations after update
SELECT
    e.id as equipment_id,
    e.name as equipment_name,
    e.designation as equipment_designation,
    m.id as meter_id,
    m.numero_compteur as meter_numero,
    m.description as meter_description
FROM equipment e
LEFT JOIN meter m ON e.meter_id = m.id
ORDER BY e.id;

-- Show count of associated vs non-associated equipment
SELECT
    SUM(CASE WHEN meter_id IS NOT NULL THEN 1 ELSE 0 END) as associated_equipment,
    SUM(CASE WHEN meter_id IS NULL THEN 1 ELSE 0 END) as non_associated_equipment,
    COUNT(*) as total_equipment
FROM equipment;
